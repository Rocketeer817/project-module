- This service is a product service which uses apis to maintain product catalogue
- In the product service, first we configured it with a fakeProductStore instead of DB
- Then we replaced the fakeProductService with a MySQL DB
- Then we used pagination concepts for search functionality to return the product results in pages
- Then we configured our service with an Elastic service for faster results due to indexing

- Then it is configured with user service for authentication and authorization as seen in security folder

- The url for user service :
https://github.com/Rocketeer817/userservice

- There is another service configured for payments where I integrated payment Gateways of RazorPay and Stripe
This is called Payment Service
https://github.com/Rocketeer817/paymentservice

