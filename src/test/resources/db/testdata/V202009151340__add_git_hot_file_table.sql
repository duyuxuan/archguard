create table git_hot_file
(
    system_id              bigint      not null,
    repo                   char(50)    null,
    name                   char(500)   not null,
    module_name            char(50)    null,
    class_name             char(255)   null,
    jclass_id              char(36)    null,
    modified_count         int         not null
);