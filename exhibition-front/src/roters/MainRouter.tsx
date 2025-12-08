import VenueDetail from "@/components/venue/VenueDetail";
import VenueList from "@/components/venue/VenueList";
import HomePage from "@/pages/main/HomePage";
import React from "react";
import { Route, Router, Routes } from "react-router-dom";

function MainRouter() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/venues" element={<VenueList />} />
      <Route path="/venues/:venueId" element={<VenueDetail />} />
    </Routes>
  );
}

export default MainRouter;
