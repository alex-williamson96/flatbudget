<h1>Flat Budget</h1>
<p>Flat budget is simple a zero-based budgeting application.</p>

<p>Zero-based budgeting means that every dollar within the budget
is given a purpose. The budget also only contains real money,
meaning all cash, credits, and debits for the accounts being tracked.</p>

<p>This application was built using Spring Boot for the back end REST
API, React (vite) for the front end, and ProsgreSQL for the database.</p>

<p>The back end and front end are compiled together at run time and
requires maven to build and package the project. The front end is
located within the 'webapp' folder and to run locally, requires
<code>npm install</code> to be run within that directory to install 
dependencies. The readme within the webapp folder goes into more
detail about how to run the React app locally.</p>
<p>To compile the front end and backend together. <code>mvn clean install</code>
should be run in the root directory. This will run all unit tests and compile
the application.</p>

<p>To run locally, a Postgres database with the correct credentials is also
required</p>

<p>Database tests are run using Testcontainers which allows for throw-away
instances of the database to be created. Testcontainers requires Docker to
run.</p>