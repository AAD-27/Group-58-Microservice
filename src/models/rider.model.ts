import { Table, Column, Model, DataType } from "sequelize-typescript";

@Table({
  tableName: "riders",
  underscored: true,
  timestamps: false,
})
export class Rider extends Model {
  @Column({
    type: DataType.STRING(255),
    allowNull: false,
  })
  name: string;

  @Column({
    type: DataType.STRING(255),
    allowNull: false,
  })
  email: string;

  @Column({
    type: DataType.STRING(10),
    allowNull: false,
  })
  phone: string;

  @Column({
    type: DataType.DATE(),
    allowNull: false,
  })
  created_at: string;
}
