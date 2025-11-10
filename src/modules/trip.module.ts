import { Module } from "@nestjs/common";
import { DatabaseModule } from "../database/database.module";
import { TripController } from "./trip.controller";
import { TripService } from "./trip.service";
import { TripRepository } from "./trip.repository";
import { MetricsModule } from "./metrics/metrics.module";

@Module({
  imports: [DatabaseModule, MetricsModule],
  controllers: [TripController],
  providers: [TripService, TripRepository],
  exports: [TripService],
})
export class TripModule {}
