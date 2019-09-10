CREATE TABLE IF NOT EXISTS artists
(
  id   IDENTITY,
  name VARCHAR(64) NOT NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS artist_name
  ON artists(name);

CREATE TABLE IF NOT EXISTS songs
(
  id        IDENTITY,
  artist_id INT,
  name      VARCHAR(64) NOT NULL,
  votes     INT DEFAULT 0,
  FOREIGN KEY (artist_id) REFERENCES artists (id)
    ON UPDATE CASCADE
);
CREATE UNIQUE INDEX IF NOT EXISTS song_artist
  ON SONGS (artist_id, name);
