using CoffeeBrewer.Contracts.CoffeeBrewer.Method;
using CoffeeBrewer.Models;
using CoffeeBrewer.Services.Methods;
using Microsoft.AspNetCore.Mvc;

namespace CoffeeBrewer.Controllers;

public class MethodsController : ApiController
{
    private readonly IMethodService _methodService;

    public MethodsController(IMethodService methodService)
    {
        _methodService = methodService;
    }

    [HttpPost]
    public IActionResult CreateMethod(CreateMethodRequest request)
    {
        var requestToMethodResult = Method.From(request);

        if(requestToMethodResult.IsError)
        {
            return Problem(requestToMethodResult.Errors);
        }

        var method = requestToMethodResult.Value;
        var createMethodResult = _methodService.CreateMethod(method);

        return createMethodResult.Match(
            created => CreatedAtGetMethod(method),
            errors => Problem(errors)
        );
    }

    [HttpGet("{id}")]
    public IActionResult GetMethod(int id)
    {
        var getMethodResult = _methodService.GetMethod(id);

        return getMethodResult.Match(
            method => Ok(MapMethodResponse(method)),
            errors => Problem(errors)
        ); 
    }

    [HttpGet]
    public IActionResult GetMethods(int id)
    {
        var getMethodsResult = _methodService.GetMethods();

        return getMethodsResult.Match(
            methods => Ok(MapMethodResponse(methods)),
            errors => Problem(errors)
        ); 
    }

    [HttpPut("{id}")]
    public IActionResult UpsertMethod(int id, UpsertMethodRequest request)
    {
        var requestToMethodResult = Method.From(
            id,
            request
        );

        if(requestToMethodResult.IsError)
        {
            return Problem(requestToMethodResult.Errors);
        }
        
        var method = requestToMethodResult.Value;
        var upsertMethodResult = _methodService.UpsertMethod(method);

        return upsertMethodResult.Match(
            upserted => NoContent(),
            errors => Problem(errors)
        );
    }

    [HttpDelete("{id}")]
    public IActionResult DeleteMethod(int id)
    {
        var deleteMethodResult = _methodService.DeleteMethod(id);
        if(deleteMethodResult.IsError) {
            return Problem(deleteMethodResult.Errors);
        }
        return deleteMethodResult.Match(
            deleted => NoContent(),
            errors => Problem(errors)
        );
    }

    private static List<MethodResponse> MapMethodResponse(List<Method> methods)
    {
        var methodsResponse = new List<MethodResponse>();
        foreach (var method in methods)
        {
            methodsResponse.Add(MapMethodResponse(method));
        }
        return methodsResponse;
    }

    private static MethodResponse MapMethodResponse(Method method)
    {
        return new MethodResponse(
            method.Id,
            method.Name,
            method.Description
        );
    }

    private IActionResult CreatedAtGetMethod(Method method)
    {
        return CreatedAtAction(
            actionName: nameof(GetMethod),
            routeValues: new { id = method.Id },
            value: MapMethodResponse(method)
        );
    }
}