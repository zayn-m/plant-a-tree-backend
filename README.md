# plant-a-tree-backend

REST API server for shopping plants platform

## Development

### How to setup?

1. Clone the repo
2. Install Postgres v13 on your machine (pgAdmin 4 preferred)
3. Start pgAdmin4 and create user with username: postgres and password: postgres
4. Create database named `plant_a_tree` either from pgAdmin or cli
5. Once pgAdmin4 is running, open project in eclipse and start the application, this will run all the migrations to create tables
6. Now open project directory in your terminal and run `sudo -u postgres psql`. If this does not work, set environment variable by `export PATH=/Library/PostgreSQL/13/bin:$PATH` and try again
7. This will ask your machine and database password
8. Run this command to populate roles `\i /Users/{username}/{path-to-project-dir}/roles.sql`
9. Now you are all set
