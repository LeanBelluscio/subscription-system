create table IF NOT EXISTS user
(
   id IDENTITY not null,
   email VARCHAR(120) not null,
   first_name VARCHAR(120) null,
   gender VARCHAR(3) null,
   birth_date date not null,
   primary key(id)
);

create table IF NOT EXISTS campaign
(
   id IDENTITY not null,
   campaign_name VARCHAR(120) not null,
   description VARCHAR(255) not null,
   start_date date not null,
   end_date date not null,
   primary key(id)
);

create table IF NOT EXISTS subscription
(
   id IDENTITY not null,
   user_id long not null,
   campaign_id long not null,
   subscription_date date not null,
   primary key(id),
   foreign key (user_id ) references user(id),
   foreign key (campaign_id ) references campaign(id)
);

INSERT INTO campaign (id, campaign_name, description, start_date, end_date)
SELECT 1,'Summer Offers','Enjoy the Summer with Adidas','2021-06-21','2021-09-20'
WHERE NOT EXISTS (SELECT * FROM campaign);

INSERT INTO campaign (id, campaign_name, description, start_date, end_date)
SELECT 2,'Winter Offers','Enjoy the Winter with Adidas','2021-12-21','2021-03-20'
WHERE NOT EXISTS (SELECT * FROM campaign);