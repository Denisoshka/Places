// src/App.tsx

import React from 'react';
import './App.css';
import Search from './components/Search';

const App: React.FC = () => {
    return (
        <div className="App">
            <h1>Поиск с помощью API</h1>
            <Search />
        </div>
    );
};

export default App;
