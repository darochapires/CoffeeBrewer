using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CoffeeBrewer.Migrations
{
    /// <inheritdoc />
    public partial class RecipeAddColumns : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<double>(
                name: "WaterTemperature",
                table: "Recipes",
                type: "REAL",
                nullable: false,
                defaultValue: 0.0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "WaterTemperature",
                table: "Recipes");
        }
    }
}
