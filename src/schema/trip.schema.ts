import { RiderSchema } from "./rider.schema";
import { DriverSchema } from "./driver.schema";

export class TripSchema {
  id: number;
  rider_id: number;
  driver_id: number;
  pickup_zone: string;
  drop_zone: string;
  status: string;
  requested_at: string;
  distance_km: string;
  base_fare: string;
  surge_multiplier: string;
  total_fare: string;
  rider: RiderSchema;
  driver: DriverSchema;
}
