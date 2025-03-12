-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2024 a las 22:20:05
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `jdbc2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historico`
--

CREATE TABLE `historico` (
  `id` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `matricula` varchar(20) NOT NULL,
  `año` int(11) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `genero` char(1) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historico`
--

INSERT INTO `historico` (`id`, `id_persona`, `id_vehiculo`, `nombre`, `matricula`, `año`, `marca`, `modelo`, `genero`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 1, 1, 'Juan Pérez', 'ABC1234', 2010, 'Toyota', 'Corolla', 'H', '2015-03-01', '2017-06-01'),
(2, 2, 1, 'María Gómez', 'ABC1234', 2010, 'Toyota', 'Corolla', 'M', '2017-07-01', '2019-08-30'),
(3, 3, 1, 'Carlos Díaz', 'ABC1234', 2010, 'Toyota', 'Corolla', 'H', '2019-09-15', NULL),
(4, 4, 2, 'Ana Fernández', 'DEF5678', 2012, 'Honda', 'Civic', 'M', '2016-01-05', '2018-04-10'),
(5, 5, 2, 'Luis Ramírez', 'DEF5678', 2012, 'Honda', 'Civic', 'H', '2018-05-12', '2020-09-18'),
(6, 6, 2, 'Laura Martínez', 'DEF5678', 2012, 'Honda', 'Civic', 'M', '2021-01-25', NULL),
(7, 7, 3, 'Miguel Torres', 'GHI9012', 2015, 'Ford', 'Focus', 'H', '2022-07-12', NULL),
(8, 8, 4, 'Sofía López', 'JKL3456', 2013, 'Chevrolet', 'Malibu', 'M', '2017-06-06', '2019-07-22'),
(9, 9, 4, 'Fernando Morales', 'JKL3456', 2013, 'Chevrolet', 'Malibu', 'H', '2019-08-01', NULL),
(10, 10, 5, 'Isabel Blanco', 'MNO7890', 2011, 'Hyundai', 'Elantra', 'M', '2015-02-15', '2017-03-05'),
(11, 11, 5, 'José García', 'MNO7890', 2011, 'Hyundai', 'Elantra', 'H', '2017-04-10', '2018-12-20'),
(12, 12, 5, 'Patricia González', 'MNO7890', 2011, 'Hyundai', 'Elantra', 'M', '2019-01-15', '2020-11-30'),
(13, 13, 6, 'Raúl Sánchez', 'PQR2345', 2016, 'Nissan', 'Altima', 'H', '2018-07-01', '2020-05-18'),
(14, 14, 6, 'Lorena Reyes', 'PQR2345', 2016, 'Nissan', 'Altima', 'M', '2021-02-20', NULL),
(17, 17, 8, 'Andrés Vega', 'VWX0123', 2017, 'Volkswagen', 'Golf', 'H', '2017-11-25', '2019-05-01'),
(18, 18, 8, 'Beatriz Soto', 'VWX0123', 2017, 'Volkswagen', 'Golf', 'M', '2019-06-15', '2021-01-20'),
(19, 19, 8, 'Héctor Delgado', 'VWX0123', 2017, 'Volkswagen', 'Golf', 'H', '2021-02-01', NULL),
(20, 20, 9, 'Verónica Ortiz', 'YZA4567', 2018, 'Kia', 'Optima', 'M', '2022-04-10', NULL),
(21, 21, 10, 'Gustavo Ruiz', 'BCD8901', 2019, 'Subaru', 'Impreza', 'H', '2019-09-01', '2021-03-10'),
(22, 22, 10, 'Carmen Paredes', 'BCD8901', 2019, 'Subaru', 'Impreza', 'M', '2021-03-15', NULL),
(23, 23, 11, 'Diego Jiménez', 'EFG2345', 2010, 'Toyota', 'Camry', 'H', '2012-02-25', '2014-09-10'),
(24, 24, 11, 'Teresa Mendoza', 'EFG2345', 2010, 'Toyota', 'Camry', 'M', '2014-10-01', '2016-11-25'),
(25, 25, 11, 'Alberto Lozano', 'EFG2345', 2010, 'Toyota', 'Camry', 'H', '2017-01-05', '2024-11-14'),
(26, 26, 12, 'Paula Castro', 'HIJ6789', 2011, 'Honda', 'Accord', 'M', '2020-11-25', NULL),
(27, 27, 13, 'Rodrigo Romero', 'KLM0123', 2012, 'Ford', 'Fusion', 'H', '2017-05-15', '2019-04-05'),
(28, 28, 13, 'Carolina Ruiz', 'KLM0123', 2012, 'Ford', 'Fusion', 'M', '2019-05-01', NULL),
(29, 29, 14, 'Jaime Ponce', 'NOP4567', 2013, 'Chevrolet', 'Impala', 'H', '2023-01-10', NULL),
(30, 30, 15, 'Natalia León', 'QRS8901', 2014, 'Hyundai', 'Sonata', 'M', '2016-06-10', '2018-08-25'),
(31, 31, 15, 'Manuel Herrera', 'QRS8901', 2014, 'Hyundai', 'Sonata', 'H', '2018-09-15', '2020-03-15'),
(32, 32, 16, 'Raquel Ramos', 'TUV2345', 2015, 'Nissan', 'Maxima', 'M', '2016-02-05', '2017-07-20'),
(33, 33, 16, 'Esteban Aguilar', 'TUV2345', 2015, 'Nissan', 'Maxima', 'H', '2017-08-01', '2019-12-05'),
(34, 34, 16, 'Irene Fuentes', 'TUV2345', 2015, 'Nissan', 'Maxima', 'M', '2020-01-10', '2024-11-14'),
(35, 35, 17, 'Rubén Campos', 'WXY6789', 2016, 'Mazda', 'Mazda6', 'H', '2014-04-15', '2016-10-10'),
(36, 36, 17, 'Clara Carrillo', 'WXY6789', 2016, 'Mazda', 'Mazda6', 'M', '2017-01-20', '2019-07-05'),
(37, 37, 18, 'Álvaro Navarro', 'ZAB0123', 2017, 'Volkswagen', 'Passat', 'H', '2018-05-25', '2020-12-01'),
(38, 38, 18, 'Eva Peña', 'ZAB0123', 2017, 'Volkswagen', 'Passat', 'M', '2021-01-05', '2022-06-15'),
(39, 39, 27, 'Tomás Castro', 'ABC6789', 2016, 'Mazda', 'CX-5', 'H', '2020-02-10', NULL),
(40, 39, 28, 'Tomás Castro', 'DEF0123', 2017, 'Volkswagen', 'Tiguan', 'H', '2021-04-18', NULL),
(41, 39, 29, 'Tomás Castro', 'GHI4567', 2018, 'Kia', 'Sportage', 'H', '2022-05-10', NULL),
(42, 40, 30, 'Lucía Morales', 'JKL8901', 2019, 'Subaru', 'Outback', 'M', '2022-08-10', NULL),
(43, 40, 31, 'Lucía Morales', 'MNO2345', 2010, 'Toyota', 'Highlander', 'M', '2022-09-15', NULL),
(44, 41, 32, 'Hugo Ferrer', 'PQR6789', 2011, 'Honda', 'CR-V', 'H', '2023-01-01', NULL),
(45, 42, 33, 'Silvia Cruz', 'STU0123', 2012, 'Ford', 'Edge', 'M', '2022-01-01', '2023-06-01'),
(46, 43, 34, 'Fabián Medina', 'VWX4567', 2013, 'Chevrolet', 'Traverse', 'H', '2021-05-12', NULL),
(47, 44, 35, 'Andrea Vázquez', 'YZA8901', 2014, 'Hyundai', 'Santa Fe', 'M', '2020-07-07', NULL),
(48, 45, 36, 'Ignacio Campos', 'BCD2345', 2015, 'Nissan', 'Murano', 'H', '2021-03-15', '2022-03-15'),
(49, 46, 37, 'Elisa Herrera', 'EFG6789', 2016, 'Mazda', 'CX-9', 'M', '2019-08-12', NULL),
(50, 47, 38, 'Pablo Luna', 'HIJ0123', 2017, 'Volkswagen', 'Atlas', 'H', '2022-12-20', NULL),
(69, 65, 56, 'José María', '890DFG', 2020, 'Corolla', 'Toyota', 'H', '2024-11-14', NULL),
(70, 65, 53, 'José María', '7829FDG', 2000, 'Chevrolet', 'Traverse', 'H', '2024-11-14', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `genero` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `nombre`, `dni`, `genero`) VALUES
(1, 'Juan Pérez', '12345678A', 'H'),
(2, 'María Gómez', '23456789B', 'M'),
(3, 'Carlos Díaz', '34567890C', 'H'),
(4, 'Ana Fernández', '45678901D', 'M'),
(5, 'Luis Ramírez', '56789012E', 'H'),
(6, 'Laura Martínez', '67890123F', 'M'),
(7, 'Miguel Torres', '78901234G', 'H'),
(8, 'Sofía López', '89012345H', 'M'),
(9, 'Fernando Morales', '90123456I', 'H'),
(10, 'Isabel Blanco', '01234567J', 'M'),
(11, 'José García', '10234567K', 'H'),
(12, 'Patricia González', '21345678L', 'M'),
(13, 'Raúl Sánchez', '32456789M', 'H'),
(14, 'Lorena Reyes', '43567890N', 'M'),
(15, 'Pedro Álvarez', '54678901O', 'H'),
(16, 'Elena Moreno', '65789012P', 'M'),
(17, 'Andrés Vega', '76890123Q', 'H'),
(18, 'Beatriz Soto', '87901234R', 'M'),
(19, 'Héctor Delgado', '98012345S', 'H'),
(20, 'Verónica Ortiz', '09123456T', 'M'),
(21, 'Gustavo Ruiz', '19234567U', 'H'),
(22, 'Carmen Paredes', '20345678V', 'M'),
(23, 'Diego Jiménez', '31456789W', 'H'),
(24, 'Teresa Mendoza', '42567890X', 'M'),
(25, 'Alberto Lozano', '53678901Y', 'H'),
(26, 'Paula Castro', '64789012Z', 'M'),
(27, 'Rodrigo Romero', '75890123A', 'H'),
(28, 'Carolina Ruiz', '86901234B', 'M'),
(29, 'Jaime Ponce', '97012345C', 'H'),
(30, 'Natalia León', '08123456D', 'M'),
(31, 'Manuel Herrera', '19234567E', 'H'),
(32, 'Raquel Ramos', '20345678F', 'M'),
(33, 'Esteban Aguilar', '31456789G', 'H'),
(34, 'Irene Fuentes', '42567890H', 'M'),
(35, 'Rubén Campos', '53678901I', 'H'),
(36, 'Clara Carrillo', '64789012J', 'M'),
(37, 'Álvaro Navarro', '75890123K', 'H'),
(38, 'Eva Peña', '86901234L', 'M'),
(39, 'Tomás Castro', '97012345M', 'H'),
(40, 'Lucía Morales', '08123456N', 'M'),
(41, 'Hugo Ferrer', '19234567O', 'H'),
(42, 'Silvia Cruz', '20345678P', 'M'),
(43, 'Fabián Medina', '31456789Q', 'H'),
(44, 'Andrea Vázquez', '42567890R', 'M'),
(45, 'Ignacio Campos', '53678901S', 'H'),
(46, 'Elisa Herrera', '64789012T', 'M'),
(47, 'Pablo Luna', '75890123U', 'H'),
(48, 'Rosa Lozano', '86901234V', 'M'),
(49, 'Daniel Gil', '97012345W', 'H'),
(50, 'Sara Sosa', '08123456X', 'M'),
(51, 'juan antonio', '12938112F', 'H'),
(52, 'Jesús Manuel Escobar', '74628938T', 'H'),
(58, 'juahn', '09876543A', 'M'),
(61, 'juanito', '94729736A', 'H'),
(62, 'Julián Martinez', '09757287A', 'H'),
(65, 'José María', '47000000N', 'H');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `id` int(11) NOT NULL,
  `matricula` varchar(20) NOT NULL,
  `año` int(11) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id`, `matricula`, `año`, `marca`, `modelo`) VALUES
(1, 'ABC1234', 2010, 'Toyota', 'Corolla'),
(2, 'DEF5678', 2012, 'Honda', 'Civic'),
(3, 'GHI9012', 2015, 'Ford', 'Focus'),
(4, 'JKL3456', 2013, 'Chevrolet', 'Malibu'),
(5, 'MNO7890', 2011, 'Hyundai', 'Elantra'),
(6, 'PQR2345', 2016, 'Nissan', 'Altima'),
(7, 'STU6789', 2014, 'Mazda', 'Mazda3'),
(8, 'VWX0123', 2017, 'Volkswagen', 'Golf'),
(9, 'YZA4567', 2018, 'Kia', 'Optima'),
(10, 'BCD8901', 2019, 'Subaru', 'Impreza'),
(11, 'EFG2345', 2010, 'Toyota', 'Camry'),
(12, 'HIJ6789', 2011, 'Honda', 'Accord'),
(13, 'KLM0123', 2012, 'Ford', 'Fusion'),
(14, 'NOP4567', 2013, 'Chevrolet', 'Impala'),
(15, 'QRS8901', 2014, 'Hyundai', 'Sonata'),
(16, 'TUV2345', 2015, 'Nissan', 'Maxima'),
(17, 'WXY6789', 2016, 'Mazda', 'Mazda6'),
(18, 'ZAB0123', 2017, 'Volkswagen', 'Passat'),
(19, 'CDE4567', 2018, 'Kia', 'Sorento'),
(20, 'FGH8901', 2019, 'Subaru', 'Forester'),
(21, 'IJK2345', 2010, 'Toyota', 'Prius'),
(22, 'LMN6789', 2011, 'Honda', 'Fit'),
(23, 'OPQ0123', 2012, 'Ford', 'Escape'),
(24, 'RST4567', 2013, 'Chevrolet', 'Equinox'),
(25, 'UVW8901', 2014, 'Hyundai', 'Tucson'),
(26, 'XYZ2345', 2015, 'Nissan', 'Rogue'),
(27, 'ABC6789', 2016, 'Mazda', 'CX-5'),
(28, 'DEF0123', 2017, 'Volkswagen', 'Tiguan'),
(29, 'GHI4567', 2018, 'Kia', 'Sportage'),
(30, 'JKL8901', 2019, 'Subaru', 'Outback'),
(31, 'MNO2345', 2010, 'Toyota', 'Highlander'),
(32, 'PQR6789', 2011, 'Honda', 'CR-V'),
(33, 'STU0123', 2012, 'Ford', 'Edge'),
(34, 'VWX4567', 2013, 'Chevrolet', 'Traverse'),
(35, 'YZA8901', 2014, 'Hyundai', 'Santa Fe'),
(36, 'BCD2345', 2015, 'Nissan', 'Murano'),
(37, 'EFG6789', 2016, 'Mazda', 'CX-9'),
(38, 'HIJ0123', 2017, 'Volkswagen', 'Atlas'),
(39, 'KLM4567', 2018, 'Kia', 'Telluride'),
(40, 'NOP8901', 2019, 'Subaru', 'Ascent'),
(41, 'QRS2345', 2010, 'Toyota', '4Runner'),
(42, 'TUV6789', 2011, 'Honda', 'Pilot'),
(43, 'WXY0123', 2012, 'Ford', 'Explorer'),
(44, 'ZAB4567', 2013, 'Chevrolet', 'Tahoe'),
(45, 'CDE8901', 2014, 'Hyundai', 'Palisade'),
(46, 'FGH2345', 2015, 'Nissan', 'Armada'),
(47, 'IJK6789', 2016, 'Mazda', 'MX-5 Miata'),
(48, 'LMN0123', 2017, 'Volkswagen', 'Beetle'),
(49, 'OPQ4567', 2018, 'Kia', 'Stinger'),
(50, 'RST8901', 2019, 'Subaru', 'WRX'),
(51, '6603LLZ', 2020, 'Nissan', 'Maxima'),
(53, '7829FDG', 2000, 'Chevrolet', 'Traverse'),
(54, '32213sda', 1900, 'Corolla', 'Toyota'),
(56, '890DFG', 2020, 'Corolla', 'Toyota');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `historico`
--
ALTER TABLE `historico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`),
  ADD KEY `id_vehiculo` (`id_vehiculo`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `matricula` (`matricula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `historico`
--
ALTER TABLE `historico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historico`
--
ALTER TABLE `historico`
  ADD CONSTRAINT `historico_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `historico_ibfk_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
