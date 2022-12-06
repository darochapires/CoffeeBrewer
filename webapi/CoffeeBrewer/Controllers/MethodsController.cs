using CoffeeBrewer.Contracts.CoffeeBrewer;
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

    [HttpGet("{id:guid}")]
    public IActionResult GetMethod(Guid id)
    {
        var getMethodResult = _methodService.GetMethod(id);

        return getMethodResult.Match(
            method => Ok(MapMethodResponse(method)),
            errors => Problem(errors)
        ); 
    }

    [HttpPut("{id:guid}")]
    public IActionResult UpsertMethod(Guid id, UpsertMethodRequest request)
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
            upserted => upserted.IsNewlyCreated ? CreatedAtGetMethod(method) : NoContent(),
            errors => Problem(errors)
        );
    }

    [HttpDelete("{id:guid}")]
    public IActionResult DeleteMethod(Guid id)
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

    private static MethodResponse MapMethodResponse(Method method)
    {
        return new MethodResponse(
            method.Id,
            method.Name,
            method.Description,
            method.LastModifiedDateTime
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