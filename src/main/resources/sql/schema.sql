-- Отключение проверок уникальности и внешних ключей на время создания таблиц
SET CONSTRAINTS ALL DEFERRED;

-- Создание схемы (если нужно)
CREATE SCHEMA IF NOT EXISTS confectionery_opros;

-- Установка схемы по умолчанию для текущей сессии
SET search_path TO confectionery_opros;

-- Создание таблицы Survey
CREATE TABLE IF NOT EXISTS Survey (
                                      survey_id BIGSERIAL PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
                                      description TEXT,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      expires_at TIMESTAMP
);

-- Создание таблицы Question
CREATE TABLE IF NOT EXISTS Question (
                                        question_id BIGSERIAL PRIMARY KEY,
                                        survey_id BIGINT NOT NULL,
                                        text TEXT NOT NULL,
                                        type VARCHAR(50) NOT NULL,
                                        FOREIGN KEY (survey_id) REFERENCES Survey(survey_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы Response
CREATE TABLE IF NOT EXISTS Response (
                                        response_id BIGSERIAL PRIMARY KEY,
                                        survey_id BIGINT NOT NULL,
                                        response_token VARCHAR(255) NOT NULL UNIQUE,
                                        submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (survey_id) REFERENCES Survey(survey_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы Answer
CREATE TABLE IF NOT EXISTS Answer (
                                      answer_id BIGSERIAL PRIMARY KEY,
                                      response_id BIGINT NOT NULL,
                                      question_id BIGINT NOT NULL,
                                      answer_text TEXT,
                                      FOREIGN KEY (response_id) REFERENCES Response(response_id) ON DELETE CASCADE ON UPDATE CASCADE,
                                      FOREIGN KEY (question_id) REFERENCES Question(question_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание индексов для ускорения поиска в таблице Answer
CREATE INDEX IF NOT EXISTS idx_question_id ON Answer(question_id);
CREATE INDEX IF NOT EXISTS idx_response_id ON Answer(response_id);

-- Создание таблицы Option
CREATE TABLE IF NOT EXISTS Option (
                                      option_id BIGSERIAL PRIMARY KEY,
                                      question_id BIGINT NOT NULL,
                                      option_text TEXT NOT NULL,
                                      FOREIGN KEY (question_id) REFERENCES Question(question_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы Metadata
CREATE TABLE IF NOT EXISTS Metadata (
                                        metadata_id BIGSERIAL PRIMARY KEY,
                                        survey_id BIGINT NOT NULL,
                                        key VARCHAR(255) NOT NULL,
                                        value TEXT,
                                        FOREIGN KEY (survey_id) REFERENCES Survey(survey_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Включение проверок после создания таблиц
SET CONSTRAINTS ALL IMMEDIATE;
