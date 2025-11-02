import { WhereOptions } from "sequelize";
import { Trip } from "src/models/trip.model";
import { TripSchema } from "src/schema/trip.schema";

export class TripRepository {
  constructor() {}

  async findOne({
    whereOptions,
    options,
  }: {
    whereOptions: WhereOptions;
    options?: Record<string, any>;
  }): Promise<Trip | null> {
    try {
      const data = await Trip.findOne({
        where: whereOptions,
        ...(options ?? {}),
      });
      return data;
    } catch (error) {
      throw error;
    }
  }

  async findAll({
    whereOptions,
    options,
  }: {
    whereOptions: WhereOptions;
    options?: Record<string, any>;
  }): Promise<Trip[]> {
    try {
      const data = await Trip.findAll({
        where: whereOptions,
        ...(options ?? {}),
      });
      return data;
    } catch (error) {
      throw error;
    }
  }

  async updateTrip({
    whereOptions,
    payload,
    options,
  }: {
    whereOptions: WhereOptions;
    payload: Partial<Trip>;
    options: Record<string, any>;
  }): Promise<[affectedCount: number]> {
    try {
      const trip = await Trip.update(payload, {
        where: whereOptions,
        ...(options ?? {}),
      });
      return trip;
    } catch (error) {
      throw error;
    }
  }

  async createTrip({
    payload,
    options,
  }: {
    payload: Partial<TripSchema>;
    options?: Record<string, any>;
  }): Promise<Trip> {
    try {
      const trip = await Trip.create(payload, options);
      return trip;
    } catch (error) {
      throw error;
    }
  }
}
