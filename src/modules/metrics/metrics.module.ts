import { Module } from "@nestjs/common";
import {
  PrometheusModule,
  makeCounterProvider,
} from "@willsoto/nestjs-prometheus";

@Module({
  imports: [PrometheusModule],
  providers: [
    makeCounterProvider({
      name: "trips_requested_total",
      help: "Total trips requested",
    }),
    makeCounterProvider({
      name: "trips_completed_total",
      help: "Total trips completed",
    }),
    makeCounterProvider({
      name: "trips_cancelled_total",
      help: "Total trips cancelled",
    }),
  ],
  exports: [
    PrometheusModule, // <-- export the module
    makeCounterProvider({
      name: "trips_requested_total",
      help: "Total trips requested",
    }),
    makeCounterProvider({
      name: "trips_completed_total",
      help: "Total trips completed",
    }),
    makeCounterProvider({
      name: "trips_cancelled_total",
      help: "Total trips cancelled",
    }),
  ],
})
export class MetricsModule {}
