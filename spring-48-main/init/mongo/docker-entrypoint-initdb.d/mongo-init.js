print("START")

// comment db
db = db.getSiblingDB('springsocial');

db.createUser(
    {
        user: 'mongoadmin',
        pwd: 'password',
        roles: [{role: 'readWrite', db: 'springsocial'}]
    }
);

db.createCollection("comment");
db.createCollection("post");
// db.createCollection("user");

print("END");
