using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CoffeeBrewer.Migrations
{
    /// <inheritdoc />
    public partial class RecipeAddCoffeeAmountField : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<double>(
                name: "CoffeeAmount",
                table: "Recipes",
                type: "REAL",
                nullable: false,
                defaultValue: 0.0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "CoffeeAmount",
                table: "Recipes");
        }
    }
}
