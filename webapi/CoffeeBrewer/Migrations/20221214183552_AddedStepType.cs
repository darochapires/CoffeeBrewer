using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CoffeeBrewer.Migrations
{
    /// <inheritdoc />
    public partial class AddedStepType : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "StepType",
                table: "Steps",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "StepType",
                table: "Steps");
        }
    }
}
