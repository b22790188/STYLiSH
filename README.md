
# STYLiSH

An e-commerce platform offering a complete user experience, including product search, registration, and login features.

## Features

- Enhances website performance through **Redis**, **CloudFront**, and **AWS S3**.
- Integrates third-party APIs (**Facebook**/**TayPay**) for easier login and fast order transactions.
- Uses **AWS RDS** with **MySQL** as the database engine, making cloud data operations easier and more secure, while communicating with the database through **JdbcTemplate**.
- Utilizes **Redis** as a **rate limiter** with a sliding window strategy to restrict the number of API calls a user can make per second.
