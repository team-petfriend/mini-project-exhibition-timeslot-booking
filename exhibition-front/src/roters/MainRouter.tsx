import HomePage from '@/pages/main/HomePage'
import React from 'react'
import { Route, Routes } from 'react-router-dom'

function MainRouter() {
  return (
    <Routes>
      <Route path='/' element={<HomePage />} />
    </Routes>
  )
}

export default MainRouter