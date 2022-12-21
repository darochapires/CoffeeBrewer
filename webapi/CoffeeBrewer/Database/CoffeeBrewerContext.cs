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
}