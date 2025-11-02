import { Module } from "@nestjs/common";
import { DatabaseModule } from "../database/database.module";
import { TripController } from "./trip.controller";
import { TripService } from "./trip.service";
import { TripRepository } from "./trip.repository";

@Module({
  imports: [DatabaseModule],
  controllers: [TripController],
  providers: [TripService, TripRepository],
  exports: [TripService],
})
export class TripModule {}
