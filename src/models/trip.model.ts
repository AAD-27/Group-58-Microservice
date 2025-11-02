import {
  Table,
  Column,
  Model,
  DataType,
  BelongsTo,
  HasOne,
} from "sequelize-typescript";
import { Rider } from "./rider.model";
import { Driver } from "./driver.model";
import { Rating } from "./rating.model";

@Table({
  tableName: "trips",
  underscored: true,
  timestamps: false,
})
export class Trip extends Model {
  @Column({
    type: DataType.INTEGER,
    primaryKey: true,
    autoIncrement: true,
  })
  declare id: number;

  @Column({
    type: DataType.INTEGER,
    allowNull: false,
  })
  rider_id: number;

  @Column({
    type: DataType.INTEGER,
    allowNull: true,
  })
  driver_id: number;

  @Column({
    type: DataType.STRING(255),
    allowNull: false,
  })
  pickup_zone: string;

  @Column({
    type: DataType.STRING(255),
    allowNull: false,
  })
  drop_zone: string;

  @Column({
    type: DataType.ENUM(
      "ONGOING",
      "ACCEPTED",
      "CANCELLED",
      "REQUESTED",
      "COMPLETED"
    ),
    allowNull: false,
    defaultValue: "ACCEPTED",
  })
  status: string;

  @Column({
    type: DataType.DATE(),
    allowNull: false,
  })
  requested_at: string;

  @Column({
    type: DataType.DECIMAL(10, 2),
    allowNull: false,
  })
  distance_km: string;

  @Column({
    type: DataType.DECIMAL(10, 2),
    allowNull: false,
  })
  base_fare: string;

  @Column({
    type: DataType.DECIMAL(10, 2),
    allowNull: false,
  })
  surge_multiplier: string;

  @Column({
    type: DataType.DECIMAL(10, 2),
    allowNull: false,
  })
  total_fare: string;

  // Associations
  @BelongsTo(() => Rider, { foreignKey: "rider_id" })
  rider: Rider;

  @BelongsTo(() => Driver, { foreignKey: "driver_id" })
  driver: Driver;

  @HasOne(() => Rating, { foreignKey: "trip_id" })
  rating: Rating;
}
