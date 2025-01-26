-- Add 'updated_at' column to 'Users' table
ALTER TABLE Users
    ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Create 'Roles' table (for user roles like ADMIN, USER, etc.)
CREATE TABLE Roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

-- Create 'User_Roles' table (mapping users to roles)
CREATE TABLE User_Roles (
                            user_id INT REFERENCES Users(id),
                            role_id INT REFERENCES Roles(id),
                            PRIMARY KEY (user_id, role_id)
);

-- Create 'User_Status' table (to track active/inactive users)
CREATE TABLE User_Status (
                             id SERIAL PRIMARY KEY,
                             user_id INT REFERENCES Users(id),
                             status VARCHAR(20) DEFAULT 'ACTIVE', -- 'ACTIVE', 'INACTIVE', 'SUSPENDED', etc.
                             last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create 'User_Logs' table (for audit purposes, tracking user activity)
CREATE TABLE User_Logs (
                           id SERIAL PRIMARY KEY,
                           user_id INT REFERENCES Users(id),
                           action VARCHAR(255),
                           timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for the new tables only if they do not exist
DO $$
BEGIN
    -- Index for 'Roles' table
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'roles' AND indexname = 'idx_roles_name') THEN
CREATE INDEX idx_roles_name ON Roles(name);
END IF;

    -- Index for 'User_Roles' table (user_id)
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'user_roles' AND indexname = 'idx_user_roles_user_id') THEN
CREATE INDEX idx_user_roles_user_id ON User_Roles(user_id);
END IF;

    -- Index for 'User_Roles' table (role_id)
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'user_roles' AND indexname = 'idx_user_roles_role_id') THEN
CREATE INDEX idx_user_roles_role_id ON User_Roles(role_id);
END IF;

    -- Index for 'User_Status' table
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'user_status' AND indexname = 'idx_user_status_user_id') THEN
CREATE INDEX idx_user_status_user_id ON User_Status(user_id);
END IF;

    -- Index for 'User_Logs' table
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'user_logs' AND indexname = 'idx_user_logs_user_id') THEN
CREATE INDEX idx_user_logs_user_id ON User_Logs(user_id);
END IF;

    -- Indexes for the existing tables
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'users' AND indexname = 'idx_username') THEN
CREATE INDEX idx_username ON Users(username);
END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'users' AND indexname = 'idx_email') THEN
CREATE INDEX idx_email ON Users(email);
END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'instruments' AND indexname = 'idx_symbol') THEN
CREATE INDEX idx_symbol ON Instruments(symbol);
END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE tablename = 'marketdata' AND indexname = 'idx_marketdata_instrument_timestamp') THEN
CREATE INDEX idx_marketdata_instrument_timestamp ON MarketData(instrument_id, timestamp);
END IF;
END $$;
