CREATE TABLE Course (
    id           bigint(20) NOT NULL AUTO_INCREMENT,
    createdAt    datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name         varchar(100) NOT NULL,
    code         varchar(50)  NOT NULL,
    description  text,
    status       enum('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',
    inactiveAt   datetime NULL,

    instructor_id bigint(20) NOT NULL,
    category_id   bigint(20) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT UC_Course_Code UNIQUE (code),

    CONSTRAINT FK_Course_Instructor FOREIGN KEY (instructor_id)
        REFERENCES User(id),

    CONSTRAINT FK_Course_Category FOREIGN KEY (category_id)
        REFERENCES Category(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
