import {
  Table,
  Column,
  Model,
  DataType,
  BelongsTo,
} from "sequelize-typescript";
import { Trip } from "./trip.model";

@Table({
  tableName: "ratings",
  underscored: true,
  timestamps: false,
})
export class Rating extends Model {
  @Column({
    type: DataType.INTEGER,
    allowNull: false,
  })
  trip_id: number;

  @Column({
    type: DataType.INTEGER,
    allowNull: true,
  })
  rider_rating: number;

  @Column({
    type: DataType.INTEGER,
    allowNull: true,
  })
  driver_rating: number;

  @Column({
    type: DataType.TEXT,
    allowNull: true,
  })
  comment: string;

  // Associations
  @BelongsTo(() => Trip, { foreignKey: "trip_id" })
  trip: Trip;
}
