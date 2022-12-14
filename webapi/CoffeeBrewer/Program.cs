using CoffeeBrewer.Database;
using CoffeeBrewer.Services.Methods;
using CoffeeBrewer.Services.Recipes;
using CoffeeBrewer.Services.Steps;
using Microsoft.AspNetCore.HttpOverrides;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);
{
    builder.Services.AddControllers();
    builder.Services.AddScoped<IMethodService, MethodService>();
    builder.Services.AddScoped<IRecipeService, RecipeService>();
    builder.Services.AddScoped<IStepService, StepService>();
    builder.Services.Configure<ForwardedHeadersOptions>(options =>
    {
        options.ForwardedHeaders =
            ForwardedHeaders.XForwardedFor | ForwardedHeaders.XForwardedProto;
    });
    builder.Services.AddDbContext<CoffeeBrewerContext>(options =>
    {
        options.UseSqlite(builder.Configuration.GetConnectionString("DefaultConnection"));
    });
}

var app = builder.Build();
{
    app.UseExceptionHandler("/error");
    app.UseHttpsRedirection();
    app.MapControllers();
    app.Run();
}