create table fansub
(
    id            serial
        constraint fansub_pk primary key,
    slug          text not null,
    name          text not null,
    url           text,
    icon_small    text,
    icon_medium   text,
    icon_large    text,
    banner_small  text,
    banner_medium text,
    banner_large  text,
    founder       text,
    founded_date  date
);

create
unique index fansub_slug_uindex on fansub (slug);