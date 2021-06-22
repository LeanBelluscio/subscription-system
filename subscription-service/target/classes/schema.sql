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