import {
  Table,
  Column,
  Model,
  DataType,
  BelongsTo,
} from "sequelize-typescript";
import { Trip } from "./trip.model";

@Table({
  tableName: "payments",
  underscored: true,
  timestamps: false,
})
export class Payment extends Model {
  @Column({
    type: DataType.INTEGER,
    allowNull: false,
  })
  trip_id: number;

  @Column({
    type: DataType.DECIMAL(10, 2),
    allowNull: false,
  })
  amount: string;

  @Column({
    type: DataType.STRING(50),
    allowNull: false,
  })
  method: string;

  @Column({
    type: DataType.ENUM("FAILED", "PENDING", "SUCCESS"),
    allowNull: false,
    defaultValue: "PENDING",
  })
  status: string;

  @Column({
    type: DataType.STRING(255),
    allowNull: false,
  })
  reference: string;

  @Column({
    type: DataType.DATE(),
    allowNull: false,
  })
  created_at: string;

  // Associations
  @BelongsTo(() => Trip, {
    foreignKey: "trip_id",
  })
  trip: Trip;
}
