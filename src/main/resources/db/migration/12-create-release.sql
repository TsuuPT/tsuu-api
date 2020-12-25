create table release (
    id               serial constraint release_pk primary key,
    fansub_id        integer not null constraint release_fansub_id_fk references fansub,
    media_id         integer not null constraint release_media_id_fk references media,
    description      text,
    description_html text,
    classifier       text,
    timestamp        timestamp
);