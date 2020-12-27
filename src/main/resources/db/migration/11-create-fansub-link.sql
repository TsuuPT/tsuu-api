create table fansub_link (
    id        serial           constraint fansub_link_pk primary key,
    fansub_id integer not null constraint fansub_link_fansub_id_fk references fansub,
    link      text    not null,
    link_type text    not null
);
