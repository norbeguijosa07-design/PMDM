SET NAMES utf8mb4;
CREATE DATABASE IF NOT EXISTS pmdm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pmdm;

DROP TABLE IF EXISTS videojuegos;
CREATE TABLE videojuegos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    imagen VARCHAR(255) NOT NULL
);

INSERT INTO videojuegos (titulo, plataforma, imagen) VALUES
('Guild Wars 2', 'PC', 'https://upload.wikimedia.org/wikipedia/en/9/96/Gw2-boxfront.png'),
('Red Dead Redemption 2', 'PlayStation 4', 'https://upload.wikimedia.org/wikipedia/en/4/44/Red_Dead_Redemption_II.jpg'),
('Crash Bandicoot', 'PlayStation 1', 'https://upload.wikimedia.org/wikipedia/commons/2/22/Crash_bandicoot_logo.png'),
('Spyro the Dragon', 'PlayStation 1', 'https://upload.wikimedia.org/wikipedia/en/1/1f/Spyro_Logo_2026.png'),
('Destiny 2', 'PC, PlayStation 4', 'https://upload.wikimedia.org/wikipedia/en/0/05/Destiny_2_%28artwork%29.jpg'),
('Grand Theft Auto VI', 'PlayStation 5', 'https://upload.wikimedia.org/wikipedia/en/4/46/Grand_Theft_Auto_VI.png'),
('Minecraft', 'PC', 'https://upload.wikimedia.org/wikipedia/commons/8/84/Minecraft_Logo_%28higher_quality%29.png'),
('Osu!', 'PC', 'https://upload.wikimedia.org/wikipedia/commons/2/29/Osu%21_logo_2024.png'),
('Among Us', 'Multiplataforma', 'https://upload.wikimedia.org/wikipedia/en/9/9a/Among_Us_cover_art.jpg');
