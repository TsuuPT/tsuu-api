create table media (
    id               serial constraint media_pk primary key,
    slug             text not null,
    title_portuguese text,
    title_english    text,
    title_romanji    text,
    title_original   text,
    type             text,
    cover_small      text,
    cover_medium     text,
    cover_large      text,
    description      text,
    description_html text
);

create
    unique index media_slug_uindex on media (slug);