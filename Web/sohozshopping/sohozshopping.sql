-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2017 at 07:39 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sohozshopping`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer_shopping`
--

CREATE TABLE `customer_shopping` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` int(11) NOT NULL,
  `confirm` int(11) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_shopping`
--

INSERT INTO `customer_shopping` (`product_id`, `product_name`, `product_price`, `confirm`, `username`) VALUES
(3, 'Xiaomi Redmi Note 4', 17500, 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `product_list`
--

CREATE TABLE `product_list` (
  `id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` int(11) NOT NULL,
  `product_description` text NOT NULL,
  `product_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_list`
--

INSERT INTO `product_list` (`id`, `product_name`, `product_price`, `product_description`, `product_image`) VALUES
(3, 'Xiaomi Redmi Note 4', 17500, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(5, 'Samsung Galaxy J7', 19000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(6, 'iphone 6s+', 120000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(7, 'Samsung S8', 70000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(8, 'Samsung galaxy grand prime', 15000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(9, 'Nokia N6', 80000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(10, 'Samsung Guru Music', 1750, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(11, 'Samsung Galaxy S6', 15000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(12, 'Xioami Redmi 4A ', 10500, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(13, 'Xiaomi 5 ', 20000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', ''),
(14, 'Xiaomi 5+', 25000, 'Marshmallow 6.0\r\nBack Camera 16px\r\nFront Camera 5px\r\n1.5 octacore', '');

-- --------------------------------------------------------

--
-- Table structure for table `user_registration`
--

CREATE TABLE `user_registration` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `full_address` text NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_registration`
--

INSERT INTO `user_registration` (`id`, `first_name`, `last_name`, `username`, `email`, `city`, `full_address`, `password`) VALUES
(13, 'Mahmud', 'Hasan', 'shakkhor', '', 'Mymensingh', 'B.A.U.', 'mahmud');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer_shopping`
--
ALTER TABLE `customer_shopping`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `product_list`
--
ALTER TABLE `product_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_registration`
--
ALTER TABLE `user_registration`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_shopping`
--
ALTER TABLE `customer_shopping`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `product_list`
--
ALTER TABLE `product_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `user_registration`
--
ALTER TABLE `user_registration`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
