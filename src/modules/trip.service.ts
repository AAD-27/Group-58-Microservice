import { Injectable, Inject } from "@nestjs/common";
import { TripRepository } from "./trip.repository";
import { Rider } from "src/models/rider.model";
import { Driver } from "src/models/driver.model";
import { Trip } from "src/models/trip.model";
import { plainToInstance } from "class-transformer";
import { TripSchema } from "src/schema/trip.schema";
import { Rating } from "src/models/rating.model";
import { CreateTripDto } from "./dto/create-trip.dto";
import { Sequelize } from "sequelize-typescript";

@Injectable()
export class TripService {
  constructor(
    private readonly tripRepository: TripRepository,
    @Inject("SEQUELIZE") private readonly sequelize: Sequelize
  ) {}

  private convertTripRecordToSchema(data: Trip): TripSchema {
    const trip = plainToInstance(TripSchema, data.toJSON() as TripSchema);
    return trip;
  }

  async getTrip(id: number) {
    const transaction = await this.sequelize.transaction();
    try {
      const tripDetails = await this.tripRepository.findOne({
        whereOptions: {
          id,
        },
        options: {
          include: [
            {
              model: Rider,
              as: "rider",
            },
            {
              model: Driver,
              as: "driver",
            },
            {
              model: Rating,
              as: "rating",
            },
          ],
        },
      });
      await transaction.commit();
      return tripDetails ? this.convertTripRecordToSchema(tripDetails) : null;
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }

  async createTrip(createTripDto: CreateTripDto) {
    const transaction = await this.sequelize.transaction();
    try {
      const totalFare = (
        parseFloat(createTripDto.base_fare) *
        parseFloat(createTripDto.surge_multiplier)
      ).toFixed(2);
      const payload = {
        ...createTripDto,
        status: "REQUESTED",
        total_fare: totalFare,
        requested_at: Date.now().toString(),
      }; // Example fare calculation
      const trip = await this.tripRepository.createTrip({
        payload,
        options: { transaction },
      });
      await transaction.commit();
      return trip;
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }

  async acceptTrip(id: number) {
    const transaction = await this.sequelize.transaction();
    try {
      const trip = await this.tripRepository.findOne({
        whereOptions: {
          id,
        },
        options: { transaction },
      });
      if (!trip) {
        throw new Error("Trip not found");
      }
      await this.tripRepository.updateTrip({
        whereOptions: { id },
        payload: { status: "ACCEPTED" },
        options: { transaction },
      });
      await transaction.commit();
      return { message: `Trip accepted successfully` };
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }

  async completeTrip(id: number) {
    const transaction = await this.sequelize.transaction();
    try {
      const trip = await this.tripRepository.findOne({
        whereOptions: {
          id,
        },
        options: { transaction },
      });
      if (!trip) {
        throw new Error("Trip not found");
      }
      await this.tripRepository.updateTrip({
        whereOptions: { id },
        payload: { status: "COMPLETED" },
        options: { transaction },
      });
      await transaction.commit();
      return { message: "Trip completed successfully" };
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }

  async cancelTrip(id: number) {
    const transaction = await this.sequelize.transaction();
    try {
      const trip = await this.tripRepository.findOne({
        whereOptions: {
          id,
        },
        options: { transaction },
      });
      if (!trip) {
        throw new Error("Trip not found");
      }
      const formatedTrip = this.convertTripRecordToSchema(trip);
      if (formatedTrip.status === "COMPLETED") {
        throw new Error("Trip is already completed");
      }
      await this.tripRepository.updateTrip({
        whereOptions: { id },
        payload: { status: "CANCELLED" },
        options: { transaction },
      });
      await transaction.commit();
      return { message: "Trip cancelled successfully" };
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }

  async listAllTrips(page: number = 1, limit: number = 20) {
    const transaction = await this.sequelize.transaction();
    try {
      const offset = (page - 1) * limit;
      const trips = await this.tripRepository.findAll({
        whereOptions: {},
        options: {
          include: [
            {
              model: Rider,
              as: "rider",
            },
            {
              model: Driver,
              as: "driver",
            },
            {
              model: Rating,
              as: "rating",
            },
          ],
          offset,
          limit,
          transaction,
        },
      });
      await transaction.commit();
      return trips.map((trip) => this.convertTripRecordToSchema(trip));
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }

  async getTripETA(id: number) {
    const transaction = await this.sequelize.transaction();
    try {
      const trip = await Trip.findOne({
        where: {
          id,
          status: "ONGOING",
        },
        transaction,
      });

      if (!trip) {
        throw new Error("Trip not found or not in ONGOING status");
      }

      const tripData = this.convertTripRecordToSchema(trip);
      console.log(tripData);

      // Calculate ETA based on distance and requested_at
      const distance = parseFloat(trip.distance_km);
      console.log("distance", trip.distance_km);
      const timeElapsed =
        (new Date().getTime() - new Date(tripData.requested_at).getTime()) /
        60000; // in minutes
      console.log("timeElapsed", timeElapsed);
      const averageSpeed = 40; // Assume average speed is 40 km/h
      const estimatedTime = (distance / averageSpeed) * 60; // in minutes
      console.log("estimatedTime", estimatedTime);
      const eta = Math.max(estimatedTime - timeElapsed, 0); // Remaining time in minutes
      console.log("eta", eta);

      await transaction.commit();
      return { tripId: tripData.id, etaMinutes: Math.ceil(eta) };
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }

  async assignDriverToTrip(tripId: number, driverId: number) {
    const transaction = await this.sequelize.transaction();
    try {
      const trip = await this.tripRepository.updateTrip({
        whereOptions: { id: tripId },
        payload: { driver_id: driverId },
        options: { transaction },
      });
      await transaction.commit();
      return { message: `Driver assigned to trip successfully` };
    } catch (error) {
      await transaction.rollback();
      throw error;
    }
  }
}
