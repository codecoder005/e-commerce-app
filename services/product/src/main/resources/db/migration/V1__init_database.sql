create table if not exists category(
    id uuid primary key,
    name varchar(255),
    description varchar(255)
);

create table if not exists products(
    id uuid primary key,
    name varchar(255) not null,
    description varchar(255),
    available_quantity bigint not null,
    price numeric(38, 2),
    category_id uuid,
    constraint fk_category_id foreign key(category_id) references category(id)
);