using Microsoft.EntityFrameworkCore;
using CoffeeBrewer.Models;

namespace CoffeeBrewer.Database;

public class CoffeeBrewerContext : DbContext
{
    public DbSet<Method> Methods { get; set; }
    public DbSet<Recipe> Recipes { get; set; }
    public DbSet<Step> Steps { get; set; }
    

    public CoffeeBrewerContext(DbContextOptions<CoffeeBrewerContext> options) : base(options)
    {
        
    }

    // protected override void OnModelCreating(ModelBuilder modelBuilder)
    // {
    //     modelBuilder.Entity<Method>().HasKey(m => new {  });
    // }

    // protected override void OnConfiguring(DbContextOptionsBuilder options) => 
    //     options.UseSqlite(@"Data Source=./Database/CoffeeBrewer.db");
}