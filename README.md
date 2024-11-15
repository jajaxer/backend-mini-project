# backend-mini-project
Having two microservices:
Customer-management-service running on port 8070
Order-management-service running on port 8071
Both services are using h2 database.
Order-management-service is communicating with customer management service while making order.
By making order you  pass CustomerId as parameter in Postman  together with OrderRequest body then order will be created.
The Order service is using RestTemplate to communicate customerService to get  Customer details.