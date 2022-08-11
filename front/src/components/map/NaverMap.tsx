import React, { useEffect, useRef } from "react";

function NaverMap() {
    const mapRef = useRef<naver.maps.Map | undefined | null>();
    const markerRef = useRef<[naver.maps.Marker]>();

    useEffect(() => {
        const initMap = () => {
            mapRef.current = new naver.maps.Map("map", {
                center: new naver.maps.LatLng(37.511337, 127.012084), //현재 접속한 사용자 위치 알아낼 수 있음?
                zoom: 13,
                zoomControl: true,
                tileTransition: true, // 지도 타일 전환 시 페이드 인 효과
                // scaleControl: false, // 우측하단 축척 단위가 계속 중복이 되면 지우셈.
                // scrollWheel: false, // 마우스 스크롤 사용 여부,
                overlayZoomEffect: null, // 도형, 마커 등 오버레이 줌 효과 적용 <-> 'all',
                zoomControlOptions: {
                    position: naver.maps.Position.RIGHT_TOP,
                }
            });
            markerRef.current = [new naver.maps.Marker({
                position: new naver.maps.LatLng(37.4979517, 127.0276188),
                map: mapRef.current,
                animation: 2
            })]

            window.addEventListener('resize', () => {
                const mapWidth = window.innerWidth;
                const mapHeight = window.innerHeight;
                const fixedSize = new naver.maps.Size(mapWidth, mapHeight);
                mapRef.current.setSize(fixedSize);
            });

            mapRef.current.addListener('idle', function () {
                updateMarkers(mapRef.current, markerRef.current)
            })
        };

        initMap();
    }, []);

    const updateMarkers = (map: naver.maps.Map, markers: [naver.maps.Marker]) => {

        let mapBounds = map.getBounds();
        let marker, position;

        for (let i = 0; i < markers.length; i++) {

            marker = markers[i];
            // position = marker.getPosition();
            position = marker.getDrawingRect();

            if (mapBounds.hasBounds(position)) {
                showMarker(map, marker);
            } else {
                hideMarker(map, marker);
            }
        }
    }

    const showMarker = (map: naver.maps.Map, marker: naver.maps.Marker) => {

        if (marker.getMap()) return;
        marker.setMap(map);
    }

    const hideMarker = (map: naver.maps.Map, marker: naver.maps.Marker) => {

        if (!marker.getMap()) return;
        marker.setMap(null);
    }

    useEffect(() => {
        const markerClickEvent = (marker: naver.maps.Marker) => {
            naver.maps.Event.addListener(marker, 'click', (e: any) => {

                // 선택한 마커로 부드럽게 이동합니다.
                mapRef.current.panTo(e?.coord);
            })
        };
        if (Array.isArray(markerRef.current)) {
            for (let key of markerRef.current) {
                markerClickEvent(key);
            }
        }
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