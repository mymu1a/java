CREATE TABLE time_converter (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                local_time TIMESTAMP,
                                gmt_time TIMESTAMP
);

CREATE TABLE time_data (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           milliseconds BIGINT
);

CREATE TABLE time_device_data (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  device_time TIMESTAMP,
                                  time_converter_id BIGINT,
                                  FOREIGN KEY (time_converter_id) REFERENCES time_converter(id)
);
