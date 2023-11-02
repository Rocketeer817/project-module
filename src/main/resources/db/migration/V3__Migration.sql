ALTER TABLE product
    ADD rating DOUBLE NULL;

ALTER TABLE product
    MODIFY rating DOUBLE NOT NULL;

DROP TABLE categories_seq;

DROP TABLE product_seq;