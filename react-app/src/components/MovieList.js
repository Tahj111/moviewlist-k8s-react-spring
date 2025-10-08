import React, { useState, useEffect } from 'react';
import axios from 'axios';

const MovieList = () => {
  const [movies, setMovies] = useState([]);
  const [newMovie, setNewMovie] = useState({ title: '', director: '', year: '' });
  const [editMovie, setEditMovie] = useState(null);

  const API_URL = process.env.REACT_APP_API_URL || 'http://spring-service:8080/api';

  useEffect(() => {
    fetchMovies();
  }, []);

  const fetchMovies = async () => {
    try {
      const response = await axios.get(`${API_URL}/movies`);
      setMovies(response.data);
    } catch (error) {
      console.error('Error fetching movies:', error);
    }
  };

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      await axios.post(`${API_URL}/movies`, newMovie);
      setNewMovie({ title: '', director: '', year: '' });
      fetchMovies();
    } catch (error) {
      console.error('Error creating movie:', error);
    }
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`${API_URL}/movies/${editMovie.id}`, editMovie);
      setEditMovie(null);
      fetchMovies();
    } catch (error) {
      console.error('Error updating movie:', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`${API_URL}/movies/${id}`);
      fetchMovies();
    } catch (error) {
      console.error('Error deleting movie:', error);
    }
  };

  return (
    <div className="container">
      <h2>Movie List</h2>
      
      {/* Create Form */}
      <form onSubmit={handleCreate}>
        <h3>Add New Movie</h3>
        <input
          type="text"
          placeholder="Title"
          value={newMovie.title}
          onChange={(e) => setNewMovie({ ...newMovie, title: e.target.value })}
        />
        <input
          type="text"
          placeholder="Director"
          value={newMovie.director}
          onChange={(e) => setNewMovie({ ...newMovie, director: e.target.value })}
        />
        <input
          type="number"
          placeholder="Year"
          value={newMovie.year}
          onChange={(e) => setNewMovie({ ...newMovie, year: e.target.value })}
        />
        <button type="submit">Add Movie</button>
      </form>

      {/* Movie List */}
      <div className="movie-list">
        {movies.map((movie) => (
          <div key={movie.id} className="movie-item">
            {editMovie && editMovie.id === movie.id ? (
              <form onSubmit={handleUpdate}>
                <input
                  type="text"
                  value={editMovie.title}
                  onChange={(e) => setEditMovie({ ...editMovie, title: e.target.value })}
                />
                <input
                  type="text"
                  value={editMovie.director}
                  onChange={(e) => setEditMovie({ ...editMovie, director: e.target.value })}
                />
                <input
                  type="number"
                  value={editMovie.year}
                  onChange={(e) => setEditMovie({ ...editMovie, year: e.target.value })}
                />
                <button type="submit">Save</button>
                <button onClick={() => setEditMovie(null)}>Cancel</button>
              </form>
            ) : (
              <>
                <h3>{movie.title}</h3>
                <p>Director: {movie.director}</p>
                <p>Year: {movie.year}</p>
                <button onClick={() => setEditMovie(movie)}>Edit</button>
                <button onClick={() => handleDelete(movie.id)}>Delete</button>
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default MovieList;