# Retailer Reward Points Calculator

## How to execute the Code:

1. Check out the code from git repo to your favorite IDE (Eclipse/IntelliJ/STS)
2. Once the project is impoted into the workspace, right click on the project and select "Refresh Gradle Project".
3. Once gradle updates all the dependencies, go to gradle tasks view and execute "build" task.
4. Once you have a successful build, execute "bootrun" task which runs as springboot project.
5. Open the above mentioned URI in a browser or hit the same URI in postman.

## How to execute the Test Cases:

1. Right click on the RetailerRewardsTestSuite.java file and run or debug as JUnit
2. If configured properly, Junit view will show the progress and results.



## Dockerization:

1. Open a cmd window and go to the root of this project in your local workspace
2. Execute the following commands in this order :-

    2a. docker build -t rewardscal .
    2b. docker run rewardscal -p 8033:8080 -itd exec bash
    (Now you can access API endpoint at localhost:8080 port
    2c. docker tag rewardscal registry/repoaddress
    2d. docker push

## Health Check endpoint :
You can check if the API is up and running by hitting the below URI
http://localhost:8056/actuator/health

### How the Implementation works:

1. The mock data is loaded through coonstructor based injection
2. Once the applicable/available reward points are calculated, the state of those transactions are updated to "REWARDED" by setting a boolean flag
3. As the status is updated, sending the same request will result in an error. The error images are provided up above.

While running a docker container, the port shall be changed to 8080 (Instructions are up above)


**Environment**
Java 17, Gradle 8 or above, IDE like eclipse or sts

## API specification:

It doesn't have authentication/authorization feature, just a plain mapping.

EndPointType : GET
EndPoint URI/ URL : http://localhost:8033/api/v1/customer/{customerId}/rewards

### ResponseFormat: 
                    String customerId, Integer averageRewardsPerMonth, Integer accruedRewardsPerQuarter

<h2> DataSet </h2>

**Customer List**
1. *Before Reward Points for Transactions are calculated*

| CustomerId | AverageRewardsPerMonth | AccruedRewardsPerQuarter |
|------------|------------------------|--------------------------|
| C1         | 0                      | 0                        |
| C2         | 0                      | 0                        |
| C3         | 0                      | 0                        |
| C4         | 0                      | 0                        |
| C5         | 0                      | 0                        |

2. *After Reward Points for Transactions are calculated*

| CustomerId | AverageRewardsPerMonth | AccruedRewardsPerQuarter |
|------------|------------------------|--------------------------|
| C1         | 50                     | 16                       |
| C2         | 122                    | 40                       |
| C3         | 19                     | 6                        |
| C4         | 0                      | 0                        |
| C5         | 19                     | 6                        |

**Transaction Details List**

| isTransactionAlreadyRewarded | currencyInfo | totalSpentAmount | transactionDate | transactionId | customerId |
|------------------------------|--------------|------------------|-----------------|---------------|------------|
| true                         | USD          | 33.0             | 2022-12-12      | 1             | C1         |
| false                        | USD          | 100.0            | 2022-12-12      | 2             | C1         |
| false                        | USD          | 111.0            | 2022-12-12      | 3             | C2         |
| true                         | USD          | 222.22           | 2022-12-12      | 4             | C2         |
| false                        | USD          | 44.0             | 2022-12-12      | 5             | C3         |
| false                        | USD          | 25.0             | 2022-12-12      | 6             | C3         |
| true                         | USD          | 999.99           | 2022-12-12      | 7             | C4         |
| false                        | USD          | 49.99            | 2022-12-12      | 8             | C4         |
| true                         | USD          | 11.99            | 2022-12-12      | 9             | C5         |
| false                        | USD          | 69.99            | 2022-12-12      | 10            | C5         |


**Results**

**Customer 1**
![Snapshot -1 ](https://github.com/DurgaChilukuri/Retailer-Rewards-V1.0/blob/Retailer-Rewards-V1.1/src/main/resources/images/Customer1.png)

**Customer 2**
![Snapshot -2 ](https://github.com/DurgaChilukuri/Retailer-Rewards-V1.0/blob/Retailer-Rewards-V1.1/src/main/resources/images/Customer2.jpg)

**Customer 3**
![Snapshot -3 ](https://github.com/DurgaChilukuri/Retailer-Rewards-V1.0/blob/Retailer-Rewards-V1.1/src/main/resources/images/Customer3.jpg)

**Customer 4**
![Snapshot -4 ](https://github.com/DurgaChilukuri/Retailer-Rewards-V1.0/blob/Retailer-Rewards-V1.1/src/main/resources/images/Customer4.jpg)

**Customer 5**
![Snapshot -5 ](https://github.com/DurgaChilukuri/Retailer-Rewards-V1.0/blob/Retailer-Rewards-V1.1/src/main/resources/images/Customer6.jpg)

**Error Messages**

![Snapshot -6 ](https://github.com/DurgaChilukuri/Retailer-Rewards-V1.0/blob/Retailer-Rewards-V1.1/src/main/resources/images/CustomerNotFound.jpg)

![Snapshot -7 ](https://github.com/DurgaChilukuri/Retailer-Rewards-V1.0/blob/Retailer-Rewards-V1.1/src/main/resources/images/RewardsRedeemed.jpg)




## How to execute the Code:

1. Check out the code from git repo to your favorite IDE (Eclipse/IntelliJ/STS)
2. Once the project is impoted into the workspace, right click on the project and select "Refresh Gradle Project".
3. Once gradle updates all the dependencies, go to gradle tasks view and execute "build" task.
4. Once you have a successful build, execute "bootrun" task which runs as springboot project.
5. Open the above mentioned URI in a browser or hit the same URI in postman.

## How to execute the Test Cases:

1. Right click on the RetailerRewardsTestSuite.java file and run or debug as JUnit
2. If configured properly, Junit view will show the progress and results.



## Dockerization:

1. Open a cmd window and go to the root of this project in your local workspace
2. Execute the following commands in this order :-

    2a. docker build -t rewardscal .
    2b. docker run rewardscal -p 8033:8080 -itd exec bash
    (Now you can access API endpoint at localhost:8080 port
    2c. docker tag rewardscal registry/repoaddress
    2d. docker push

## Health Check endpoint :
You can check if the API is up and running by hitting the below URI
http://localhost:8056/actuator/health

### How the Implementation works:

1. The mock data is loaded through coonstructor based injection
2. Once the applicable/available reward points are calculated, the state of those transactions are updated to "REWARDED" by setting a boolean flag
3. As the status is updated, sending the same request will result in an error. The error images are provided up above.

While running a docker container, the port shall be changed to 8080 (Instructions are up above)


**Environment**
Java 17, Gradle 8 or above, IDE like eclipse or sts
