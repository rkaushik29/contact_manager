# Contact Manager

*Time Spent Coding: 13 hours*

## Prerequisites
This application is built in Java using JPA, H2 and Spring Boot. Please ensure you have JDK17+ installed before running.
Also, ensure you have Eclipse IDE to run the server.

## Running the Server
- Open the project by importing a maven project on Eclipse.
- Click on the `Run` button on the Eclipse IDE terminal to start the server.

## Testing Endpoints
- Open Postman, and import `contact_manager_api.postman_collection.json` which contains endpoints to test.
- First run the `Add Contact` request. In the Query Params, add contact info (without `""`)
- Run `Show Contacts` only after adding >= 1 contacts. Check here for contact IDs to use when deleting contacts.
- Run `Update Contacts` only after adding >= 1 contacts.
- Run `Delete Contact` only after adding a contact.
- Run `Search` after adding a contact, and you can search by first or last name (even partial names).

Note: If you run the latter endpoints before `Add` they will not kill the server, but you won't get anything meaningful from it apart from an error message.

## System
The system contains:
- Model Entities: Contact and Notes
- Repository Interfaces for the above.
- Service Classes for business logic involving contacts and notes
- Controller for Contacts to define endpoints for data access and manipulation.
- DTO classes for transferring data through different application levels.


[GPT Conversation](https://chat.openai.com/share/46a5c29f-3a1f-4417-8f7d-f6ac2f02bbd0)

I used GPT in this assessment to discuss tradeoffs on approaches, explanation of concepts I don't yet understand (since I don't have highly advanced knowledge in Java), and debugging some code in the end since I was running short on time. I did not use GPT for any other code generation. The screenshots are in the `img` folder.

## Improvements
- I attempted to add Swagger and Junit tests, but fell short on time. This would help onboarding clients to the API.
- Adding functionality to search by note text.
- Converting the application into a distributed system of multiple servers that has local caches, load balancing, and utilizes CDNs can offload the servers (Global reach).
- Concurrency within the API can speed up responses.
- Dividing the components into microservices can help integrate future technology and allow independent scaling of each component.
- Using a cloud service will help scaling the API.
- Data encryption can safeguard user data.
