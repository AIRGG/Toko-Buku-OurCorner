-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2021 at 03:58 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tutorial3_mrtom`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_buku`
--

CREATE TABLE `tbl_buku` (
  `id_buku` varchar(5) NOT NULL,
  `judul` varchar(99) NOT NULL,
  `penulis_id` varchar(5) NOT NULL,
  `penerbit_id` varchar(5) NOT NULL,
  `genre_id` varchar(5) NOT NULL,
  `sinopsis` text NOT NULL,
  `harga` double NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_buku`
--

INSERT INTO `tbl_buku` (`id_buku`, `judul`, `penulis_id`, `penerbit_id`, `genre_id`, `sinopsis`, `harga`, `stok`) VALUES
('B96', 'ini bukan buku', 'PE01', 'PEN01', 'G1', 'jangan dibaca', 50000, 12),
('B99', 'INI BUKAN BUKU2', 'PE01', 'PEN01', 'G2', 'jangan dibaca', 5000, 20),
('BK01', 'Sejarah 45', 'PE01', 'PEN01', 'G1', 'ini buku adalah tentang menjelaskan soal suatu sejarah ketika saat menjelang pada 1945', 5000, 12),
('BK02', 'INI ADALAH BUKU', 'PE01', 'P1', 'G2', 'ini text yang panjang', 1000, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_det_trx`
--

CREATE TABLE `tbl_det_trx` (
  `trx_id` varchar(5) NOT NULL,
  `buku_id` varchar(5) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_det_trx`
--

INSERT INTO `tbl_det_trx` (`trx_id`, `buku_id`, `qty`, `harga`) VALUES
('A3', 'B96', 5, 250000),
('A3', 'BK01', 3, 15000),
('A3', 'BK02', 7, 7000),
('T01', 'BK01', 2, 10000),
('T01', 'BK02', 3, 3000),
('T09', 'B99', 5, 25000),
('T09', 'BK01', 2, 10000),
('T09', 'BK02', 7, 7000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_genre`
--

CREATE TABLE `tbl_genre` (
  `id_genre` varchar(5) NOT NULL,
  `nama` varchar(55) NOT NULL,
  `keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_genre`
--

INSERT INTO `tbl_genre` (`id_genre`, `nama`, `keterangan`) VALUES
('G1', 'History', 'Tentang Sejarah'),
('G2', 'Math', 'Tentang Matematika'),
('G3', 'TIK', 'Teknologi Informasi and Komunikasi');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pelanggan`
--

CREATE TABLE `tbl_pelanggan` (
  `id_pelanggan` varchar(5) NOT NULL,
  `nama` varchar(55) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `email` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_pelanggan`
--

INSERT INTO `tbl_pelanggan` (`id_pelanggan`, `nama`, `alamat`, `no_telp`, `email`) VALUES
('1', 'asd', 'asd', 'asd', 'sad'),
('2', 'asd', 'asd', 'asd', 'asd'),
('5', 'asd', 'asd', 'asd', 'asd'),
('P01', 'ORANG', 'jauhh', '82173', 'iniemail@mail.com'),
('P02', 'Saya Orang Baru', 'jauh sekali', '9217635', 'iniemailku@mail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_penerbit`
--

CREATE TABLE `tbl_penerbit` (
  `id_penerbit` varchar(5) NOT NULL,
  `nama` varchar(55) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `email` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_penerbit`
--

INSERT INTO `tbl_penerbit` (`id_penerbit`, `nama`, `alamat`, `no_telp`, `email`) VALUES
('P1', 'zzz', 'zzz', 'zzz', 'zzz'),
('PEN01', 'PT ABC', 'Depok', '021897', 'acb@email.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_penulis`
--

CREATE TABLE `tbl_penulis` (
  `id_penulis` varchar(5) NOT NULL,
  `nama` varchar(55) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `email` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_penulis`
--

INSERT INTO `tbl_penulis` (`id_penulis`, `nama`, `alamat`, `no_telp`, `email`) VALUES
('1', 'ssss', 'aaaa', 'b', 'asd'),
('P91', 'zz', 'zz', 'zz', 'zz'),
('PE01', 'Aku', 'jauhh', '08123', 'iniemail@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_trx`
--

CREATE TABLE `tbl_trx` (
  `id_trx` varchar(5) NOT NULL,
  `tanggal` date NOT NULL,
  `pelanggan_id` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_trx`
--

INSERT INTO `tbl_trx` (`id_trx`, `tanggal`, `pelanggan_id`) VALUES
('A3', '2021-09-30', 'P02'),
('T01', '2021-09-15', 'P01'),
('T09', '2021-10-03', 'P01');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(55) NOT NULL,
  `password` varchar(55) NOT NULL,
  `role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'toma', 'toma', 'admin'),
(2, 'gg', '123', 'admin'),
(3, 'orang', '123', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_buku`
--
ALTER TABLE `tbl_buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indexes for table `tbl_det_trx`
--
ALTER TABLE `tbl_det_trx`
  ADD PRIMARY KEY (`trx_id`,`buku_id`);

--
-- Indexes for table `tbl_genre`
--
ALTER TABLE `tbl_genre`
  ADD PRIMARY KEY (`id_genre`);

--
-- Indexes for table `tbl_pelanggan`
--
ALTER TABLE `tbl_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indexes for table `tbl_penerbit`
--
ALTER TABLE `tbl_penerbit`
  ADD PRIMARY KEY (`id_penerbit`);

--
-- Indexes for table `tbl_penulis`
--
ALTER TABLE `tbl_penulis`
  ADD PRIMARY KEY (`id_penulis`);

--
-- Indexes for table `tbl_trx`
--
ALTER TABLE `tbl_trx`
  ADD PRIMARY KEY (`id_trx`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
