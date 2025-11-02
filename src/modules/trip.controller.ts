import {
  Controller,
  Get,
  Param,
  Post,
  Patch,
  Body,
  Query,
} from "@nestjs/common";
import { TripService } from "./trip.service";
import { CreateTripDto } from "./dto/create-trip.dto";

@Controller("v1")
export class TripController {
  constructor(private readonly tripService: TripService) {}
  @Get("/trips")
  async listAllTrips(@Query("page") page: number = 1) {
    try {
      const data = await this.tripService.listAllTrips(page);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }

  @Get("/trips/:id")
  async getTrips(@Param("id") id: number) {
    try {
      const data = await this.tripService.getTrip(id);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }

  @Post("/trips")
  async createTrip(@Body() createTripDto: CreateTripDto) {
    try {
      const data = await this.tripService.createTrip(createTripDto);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }

  @Patch("/trips/:id/accept")
  async acceptTrip(@Param("id") id: number) {
    try {
      const data = await this.tripService.acceptTrip(id);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }

  @Patch("/trips/:id/complete")
  async completeTrip(@Param("id") id: number) {
    try {
      const data = await this.tripService.completeTrip(id);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }

  @Patch("/trips/:id/cancel")
  async cancelTrip(@Param("id") id: number) {
    try {
      const data = await this.tripService.cancelTrip(id);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }

  @Patch("/trips/:id/assign-driver")
  async assignDriverToTrip(
    @Param("id") id: number,
    @Body("driverId") driverId: number
  ) {
    try {
      const data = await this.tripService.assignDriverToTrip(id, driverId);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }

  @Get("/trips/:id/eta")
  async getTripETA(@Param("id") id: number) {
    try {
      const data = await this.tripService.getTripETA(id);
      return {
        status: "success",
        data,
      };
    } catch (error: any) {
      return {
        status: "error",
        message: error.message,
      };
    }
  }
}
