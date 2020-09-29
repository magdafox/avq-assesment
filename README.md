# avq-assesment

## Installation 

#### Getting the sources

From git repository:
``` bash
git clone git@github.com:magdafox/avq-assesment.git
```

#### Running the application

``` bash
mvn spring-boot:run
```

#### Testng locally

To run the REST endpoints locally you can use `RollSimulationApiIntegrationTest` integration test.

## Overview

#### Introduction

This is an application to simulate dice distribution. 

### REST Api

#### Execute dice distribution simulation

``` bash
POST http://localhost:8080/dice/simulations
```
Using query parameters you can configure:

- `diceNumber` - number of dices which will be rolled each time
- `diceSide` - number of sides of the dice
- `numberOfRolls` - number of overall rolls

Example:
``` bash
POST http://localhost:8080/dice/simulations?diceNumber=3&diceSide=6&numberOfRolls=100
```
That will simulate 3 pieces of 6-sided dices rolled 100-times. Those are also the default values if none will be 
configured.

Example of the response:

```json
{
    "results": [
        {
            "totalSum": 10,
            "count": 2
        },
        {
            "totalSum": 11,
            "count": 1
        }
  ]
}
```

That output means that sum `10` was rolled 2 times but `11` only one.

#### Read relative distribution for each dice number - dice side combination

``` bash
GET http://localhost:8080/dice/simulations/distributions
```

Example of the response:
```json
{
    "diceCombinations": [
        {
            "diceNumber": 3,
            "diceSide": 4,
            "simulationsNumber": 6,
            "rollsNumber": 600,
            "distribution": [
                {
                    "totalSum": 3,
                    "relativeDistribution": 1.00
                },
                {
                    "totalSum": 4,
                    "relativeDistribution": 4.83
                }
            ]
        }
    ]
}
```

That response means that for 3 pieces of 4-sided dice combination `6` simulations were executed, overall `600` rolls.
Moreover we can read relative distribution compared to total rolls for each `totalSum` e.g. for `totalSum=4` it is 
`4.83%`.
