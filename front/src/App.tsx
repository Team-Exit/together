import React, { useEffect } from 'react';

function App() {
  useEffect(() => {
    let map = null;
    const initMap = () => {
      const map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(37.511337, 127.012084),
        zoom: 13,
      });
    };
    initMap();
  }, []);

  //지도 사이즈 관련 스타일
  const mapStyle = {
    width: window.innerWidth,
    height: window.innerHeight
  };

  return (
    <React.Fragment>
      <div id="map" style={mapStyle}></div>
    </React.Fragment>
  );
}

export default App;
