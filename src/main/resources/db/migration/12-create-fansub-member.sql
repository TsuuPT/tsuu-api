create table fansub_member (
    id           serial           constraint fansub_member_pk primary key,
    fansub_id    integer not null constraint fansub_member_fansub_id_fk references fansub,
    name         text    not null,
    role         text,
    contact      text,
    contact_type text
);
