import React, { useEffect, useRef } from "react";

function NaverMap() {
    const mapRef = useRef<HTMLElement | null | any>(null);
    const markerRef = useRef<any | null>(null);

    useEffect(() => {
        const initMap = () => {
            mapRef.current = new naver.maps.Map("map", {
                center: new naver.maps.LatLng(37.511337, 127.012084),
                zoom: 13,
                zoomControl: true,
                zoomControlOptions: {
                    position: naver.maps.Position.RIGHT_TOP
                }
            });
            markerRef.current = new naver.maps.Marker({
                position: new naver.maps.LatLng(37.4979517, 127.0276188),
                map: mapRef.current,
            });

            window.addEventListener('resize', () => {
                const mapWidth = window.innerWidth;
                const mapHeight = window.innerHeight;
                const fixedSize = new naver.maps.Size(mapWidth, mapHeight);
                mapRef.current.setSize(fixedSize);
            });
        };

        initMap();
    }, []);

    useEffect(() => {
        const markerClickEvent = (marker: any) => {
            naver.maps.Event.addListener(marker, 'click', (e: any) => {

                // 선택한 마커로 부드럽게 이동합니다.
                mapRef.current.panTo(e?.coord);
            })
        };
        markerClickEvent(markerRef.current)
    }, [markerRef])

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

export default NaverMap;