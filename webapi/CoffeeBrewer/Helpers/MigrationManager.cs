using CoffeeBrewer.Database;
using Microsoft.EntityFrameworkCore;

namespace CoffeeBrewer.Helpers;

public static class MigrationManager
{
    public static WebApplication MigrateDatabase(this WebApplication webApp)
    {
        using (var scope = webApp.Services.CreateScope())
        {
            using (var appContext = scope.ServiceProvider.GetRequiredService<CoffeeBrewerContext>())
            {
                try
                {
                    appContext?.Database.Migrate();
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.StackTrace);
                }
            }
        }
        return webApp;
    }
}
