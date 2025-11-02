import { Table, Column, Model, DataType } from "sequelize-typescript";

@Table({
  tableName: "drivers",
  underscored: true,
  timestamps: false,
})
export class Driver extends Model {
  @Column({
    type: DataType.STRING(255),
    allowNull: false,
  })
  name: string;

  @Column({
    type: DataType.STRING(10),
    allowNull: false,
  })
  phone: string;

  @Column({
    type: DataType.STRING(50),
    allowNull: false,
  })
  vehicle_type: string;

  @Column({
    type: DataType.STRING(10),
    allowNull: false,
  })
  vehicle_plate: string;

  @Column({
    type: DataType.BOOLEAN,
    allowNull: false,
  })
  is_active: boolean;
}
